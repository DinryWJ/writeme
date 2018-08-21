package com.zust.writeme.common.util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import net.minidev.json.JSONObject;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;


/**
 * @author YFZX-WJJ-1778
 */
public class TokenUtils {

    public static void main(String[] ages) {

        //获取token
        String uid = "21321312";
        String account="wujiajiefdsfs";
        String token = createToken(uid,account);
        //解析token
        Map<String, Object> map = validToken(token);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }

    //生成token的业务逻辑
    public static String createToken(String uid,String account) {
        //获取生成token
        Map<String, Object> map = new HashMap<>();
        //建立载荷，这些数据根据业务，自己定义。
        map.put("uid", uid);
        map.put("account",account);
        //生成时间
        map.put("sta", LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
        //过期时间
        map.put("exp", LocalDateTime.now().plusHours(24).toEpochSecond(ZoneOffset.of("+8")));
        try {
            String token = TokenUtils.create(map);
            System.out.println("token=" + token);
            return token;
        } catch (JOSEException e) {
            System.out.println("生成token失败");
            e.printStackTrace();
        }
        return null;

    }

    //处理解析的业务逻辑
    public static Map<String, Object> validToken(String token) {
        //解析token
        try {
            if (token != null) {
                Map<String, Object> returnMap = new HashMap<>(16);
                Map<String, Object> validMap = TokenUtils.valid(token);
                int i = (int) validMap.get("Result");
                if (i == 0) {
                    System.out.println("token解析成功");
                    returnMap.put("success",true);
                    JSONObject jsonObject = (JSONObject) validMap.get("data");
                    returnMap.put("uid", jsonObject.get("uid"));
                    returnMap.put("account",jsonObject.get("account"));
                    returnMap.put("sta", jsonObject.get("sta"));
                    returnMap.put("exp", jsonObject.get("exp"));
                } else if (i == 2) {
                    System.out.println("token已经过期");
                    returnMap.put("success",false);
                }
                return returnMap;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 1.创建一个32-byte的密匙
     */
    private static final byte[] secret = "geiwodiangasfdjsikolkjikolkijswe".getBytes();

    //生成一个token
    private static String create(Map<String, Object> payloadMap) throws JOSEException {
        //3.先建立一个头部Header
        /**
         * JWSHeader参数：1.加密算法法则,2.类型，3.。。。。。。。
         * 一般只需要传入加密算法法则就可以。
         * 这里则采用HS256
         *
         * JWSAlgorithm类里面有所有的加密算法法则，直接调用。
         */
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
        //建立一个载荷Payload
        Payload payload = new Payload(new JSONObject(payloadMap));
        //将头部和载荷结合在一起
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        //建立一个密匙
        JWSSigner jwsSigner = new MACSigner(secret);
        //签名
        jwsObject.sign(jwsSigner);
        //生成token
        return jwsObject.serialize();
    }

    //解析一个token
    private static Map<String, Object> valid(String token) throws ParseException, JOSEException {
//        解析token
        JWSObject jwsObject = JWSObject.parse(token);
        //获取到载荷
        Payload payload = jwsObject.getPayload();
        //建立一个解锁密匙
        JWSVerifier jwsVerifier = new MACVerifier(secret);
        Map<String, Object> resultMap = new HashMap<>();
        //判断token
        if (jwsObject.verify(jwsVerifier)) {
            resultMap.put("Result", 0);
            //载荷的数据解析成json对象。
            JSONObject jsonObject = payload.toJSONObject();
            resultMap.put("data", jsonObject);
            //判断token是否过期
            if (jsonObject.containsKey("exp")) {
                Long expTime = Long.valueOf(jsonObject.get("exp").toString());
                Long nowTime = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
                //判断是否过期
                if (nowTime > expTime) {
                    //已经过期
                    resultMap.clear();
                    resultMap.put("Result", 2);
                }
            }
        } else {
            resultMap.put("Result", 1);
        }
        return resultMap;
    }
}
