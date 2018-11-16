package core.controller;

import com.sun.javafx.tk.Toolkit;
import core.vo.User;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
public class UserController {
    private static final String PROCESS_DEFINR_ID="test";



    @PostMapping("apply")
    @ResponseBody
    public Object applyFirst(User user){
        ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService=engine.getRuntimeService();
        TaskService taskService = engine.getTaskService();
        ProcessInstance vacationInstance = runtimeService.startProcessInstanceById(PROCESS_DEFINR_ID);
        Task currentTask = taskService.createTaskQuery().processInstanceId(vacationInstance.getId()).singleResult();
        taskService.claim(currentTask.getId(),user.getName());
        Map<String,Object> vars = new HashMap<>();
        vars.put("applyUser",user.getName());
        vars.put("reason",user.getReason());
        taskService.complete(currentTask.getId(),vars);
        return "success";

    }

}
