package com.fabiossilva.gerenciadorpautas.utils;

import com.fabiossilva.gerenciadorpautas.constants.ApplicationConsts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationUtils {

    @Value(value = "${hostname}")
    private String hostname;

    public String buildUrlBotao(String path) {
        return String.format(ApplicationConsts.TEMPLATE_ENDPOINT, hostname, path);
    }

}
