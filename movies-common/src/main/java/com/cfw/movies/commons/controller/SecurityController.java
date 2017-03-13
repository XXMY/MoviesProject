package com.cfw.movies.commons.controller;

import com.cfw.movies.commons.enums.ResponseTypeEnum;
import com.cfw.movies.commons.properties.CommonProperties;
import com.cfw.movies.commons.security.rsa.RSAKeyPairs;
import com.cfw.movies.commons.vo.HttpResponse;
import com.cfw.movies.commons.vo.RsaVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.interfaces.RSAPublicKey;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Duskrain on 2017/1/19.
 */
@Controller
@RequestMapping("/Security")
public class SecurityController extends BaseController{
    private Log logger = LogFactory.getLog(SecurityController.class);

    /**
     * 获得秘钥
     * @author CaiFangwei
     * @time since 2017-1-17 14:10:49
     * @return
     */
    @RequestMapping("/key")
    @ResponseBody
    public HttpResponse key(){
        String requestId = UUID.randomUUID().toString();
        HttpResponse response = new HttpResponse();

        try{
            Random r = new Random();
            int number = r.nextInt(CommonProperties.getRsaKeyPairNumber());
            RSAPublicKey publicKey = (RSAPublicKey) RSAKeyPairs.publicPrivateKeys[0].get(number);

            RsaVO rsaVO = new RsaVO();
            rsaVO.setV(number);
            rsaVO.setKey(Base64Utils.encodeToString(publicKey.getEncoded()));
            response.setData(rsaVO);
        }catch(Exception e){
            this.logger.error("[/User/key] " + e.getMessage() + "requestId="+requestId,e);
            response = buildHttpResponse(ResponseTypeEnum.SYSTEM_ERROR);
        }

        return response;

    }
}
