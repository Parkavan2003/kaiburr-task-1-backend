package com.kaiburr.taskmanger.service;

import com.kaiburr.taskmanger.model.TaskExecution;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class TaskService {
    public TaskExecution runCommand(String command) {
        TaskExecution exec = new TaskExecution();
        exec.setStartTime(new Date());

        try {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", command);
            pb.redirectErrorStream(true);
            Process process = pb.start();
            String output = new BufferedReader(
                new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))
                .lines().collect(Collectors.joining("\n"));
            exec.setOutput(output);
            process.waitFor();
        } catch (Exception e) {
            exec.setOutput("Error: " + e.getMessage());
        }

        exec.setEndTime(new Date());
        return exec;
    }
}