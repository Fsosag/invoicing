package com.invoicing.invoicing.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.invoicing.invoicing.dao.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.invoicing.invoicing.dto.UserDTO;

import java.util.List;

@Service
public class UserService {

    private static final Logger LOG = LogManager.getLogger(UserService.class);

    private final UserDAO userDAO;
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String saveUser(UserDTO userDTO){
        try {
            userDAO.saveUser(userDTO);
        }catch (Exception e){
            System.out.println(e);
        }
        return mapToJson(userDTO);
    }

    public List<UserDTO> getUsers(){
        try {
            List<UserDTO> list = userDAO.getUser();
            System.out.println(mapToJson(list));
            return list;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    protected String mapToJson(Object o) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            LOG.error("Error mapping object to json", e);
            return null;
        }
    }
}
