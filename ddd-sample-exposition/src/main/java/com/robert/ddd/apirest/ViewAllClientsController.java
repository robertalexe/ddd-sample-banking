package com.robert.ddd.apirest;

import com.robert.ddd.client.Client;
import com.robert.ddd.clients.ViewAllClients;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
public class ViewAllClientsController {

    @Autowired
    private ViewAllClients viewAllClients;

    @RequestMapping(value = "/viewallclients", method = RequestMethod.GET)
    @ApiOperation(value = "Consults all names of the clients. No input")
    public Set<String> viewAllClients() {
        return viewAllClients.viewAllClients().stream().map(Client::getUserName).collect(Collectors.toSet());
    }

}
