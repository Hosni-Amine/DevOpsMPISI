package com.example.projetmpisi.controller;

import com.example.projetmpisi.entity.User;
import com.example.projetmpisi.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User(1, "Amine Hosni", "amine.hosni@example.com");
    }

    @Test
    void testCreateUser() throws Exception {
        when(userService.saveUser(any(User.class))).thenReturn(testUser);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("Amine Hosni"))
                .andExpect(jsonPath("$.email").value("amine.hosni@example.com"));

        verify(userService, times(1)).saveUser(any(User.class));
    }

    @Test
    void testGetAllUsers() throws Exception {
        List<User> users = List.of(testUser, new User(2, "Test User", "test@example.com"));
        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testGetUserById_Success() throws Exception {
        when(userService.getUserById(1)).thenReturn(Optional.of(testUser));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("Amine Hosni"))
                .andExpect(jsonPath("$.email").value("amine.hosni@example.com"));

        verify(userService, times(1)).getUserById(1);
    }

    @Test
    void testGetUserById_NotFound() throws Exception {
        when(userService.getUserById(999)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/users/999"))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).getUserById(999);
    }

    @Test
    void testUpdateUser_Success() throws Exception {
        User updatedUser = new User(1, "Updated Name", "updated@example.com");
        when(userService.updateUser(eq(1), any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("Updated Name"))
                .andExpect(jsonPath("$.email").value("updated@example.com"));

        verify(userService, times(1)).updateUser(eq(1), any(User.class));
    }

    @Test
    void testUpdateUser_NotFound() throws Exception {
        when(userService.updateUser(eq(999), any(User.class))).thenReturn(null);

        mockMvc.perform(put("/api/users/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).updateUser(eq(999), any(User.class));
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(1);

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteUser(1);
    }
}

