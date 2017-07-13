package com.jbillote.toukabotrequests.controller;

import com.jbillote.toukabotrequests.model.CommandsResponse;
import com.jbillote.toukabotrequests.model.ImagesResponse;
import com.jbillote.toukabotrequests.model.SuccessResponse;
import com.jbillote.toukabotrequests.service.ToukaBotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class ToukaBotController {
    private static class Image {
        String command;
        String url;

        Image() {}

        Image(String command, String url) {
            this.command = command;
            this.url = url;
        }
    }

    private ToukaBotService toukaBotService;

    @Autowired
    public ToukaBotController(ToukaBotService toukaBotService) {
        this.toukaBotService = toukaBotService;
    }

    @RequestMapping(value = "/api/bot/commands", method = RequestMethod.GET)
    @ResponseBody
    public CommandsResponse getCommands() {
        return new CommandsResponse(toukaBotService.getCommands());
    }

    @RequestMapping(value = "/api/bot/images/{command}", method = RequestMethod.GET)
    @ResponseBody
    public ImagesResponse getImages(@PathVariable String command) {
        return new ImagesResponse(command, toukaBotService.getImages(command));
    }

    @RequestMapping(value = "/api/bot/images", method = RequestMethod.POST)
    @ResponseBody
    public SuccessResponse addImage(@RequestBody Image image) {
        return new SuccessResponse(toukaBotService.addImage(image.command, image.url));
    }
}
