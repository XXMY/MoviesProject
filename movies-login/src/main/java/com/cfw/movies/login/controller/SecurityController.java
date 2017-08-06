package com.cfw.movies.login.controller;

import com.cfw.movies.commons.controller.BaseController;
import com.cfw.movies.commons.enums.ResponseTypeEnum;
import com.cfw.movies.commons.vo.MoviesResponse;
import com.cfw.movies.commons.vo.RsaVO;
import com.cfw.plugins.security.properties.SecurityProperties;
import com.cfw.plugins.security.rsa.RSAKeyPairs;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.interfaces.RSAPublicKey;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Cfw on 2017/4/4.
 */
@Controller
@RequestMapping("/Login/Security")
public class SecurityController extends BaseController {
    //private Log logger = LogFactory.getLog(SecurityController.class);

    /**
     * 获得秘钥
     * @author CaiFangwei
     * @time since 2017-1-17 14:10:49
     * @return
     */
    @RequestMapping("/key")
    @ResponseBody
    public MoviesResponse key(){
        String requestId = UUID.randomUUID().toString();
        MoviesResponse response = new MoviesResponse();

        try{
            Random r = new Random();
            int number = r.nextInt(SecurityProperties.getRsaKeyPairNumber());
            RSAPublicKey publicKey = (RSAPublicKey) RSAKeyPairs.publicPrivateKeys[0].get(number);

            RsaVO rsaVO = new RsaVO();
            rsaVO.setV(number);
            rsaVO.setKey(Base64Utils.encodeToString(publicKey.getEncoded()));
            response.setData(rsaVO);
        }catch(Exception e){
            //this.logger.error("[/User/key] " + e.getMessage() + "requestId="+requestId,e);
            response = buildResponse(ResponseTypeEnum.SYSTEM_ERROR);
        }

        return response;

    }
}
