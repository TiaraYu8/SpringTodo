package com.example.todoApplication.service.impl;

import com.example.todoApplication.common.model.database.TodoModel;
import com.example.todoApplication.common.model.database.UserModel;
import com.example.todoApplication.common.model.message.TodoMessage;
import com.example.todoApplication.common.model.viewobject.TodoVO;
import com.example.todoApplication.common.model.web.form.TodoMessageProducer;
import com.example.todoApplication.repository.TodoRepository;
import com.example.todoApplication.repository.UserRepository;
import com.example.todoApplication.service.TodoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoServices {
    private TodoRepository todoRepository;
    private TodoMessage todoMessage;

    @Value("${file.upload-dir}")
    private String uploadDir;

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setTodoRepository(TodoRepository todoRepository){

        this.todoRepository = todoRepository;
    }

    public void init(){
        try {
            Files.createDirectories(Paths.get(uploadDir));
        } catch (IOException e){
            throw new RuntimeException("Could not create upload directory!", e);
        }
    }

    public  String saveImage(MultipartFile file){
        try {
            String filename = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
            Path destinationFile = Paths.get(uploadDir).resolve(filename).toAbsolutePath();
            Files.copy(file.getInputStream(), destinationFile);
            return destinationFile.toString(); // Mengembalikan path file yang disimpan
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file.", e);
        }
    }

    private TodoVO composeToViewObject(TodoModel todoModel){

        TodoVO todoVO = new TodoVO();
        todoVO.setId(todoModel.getId());
        todoVO.setTitle(todoModel.getTitle());
        todoVO.setDescription(todoModel.getDescription());
        todoVO.setCover(todoModel.getCover());
        todoVO.setIsFinished(todoModel.getIs_finished());

        return todoVO;
    }


    @Override
    public TodoVO findTodoById(String id) {
        Optional<TodoModel> todoModelOptional = todoRepository.findById(id);
        return  todoModelOptional.map(this::composeToViewObject).orElse(null);
    }

    @Override
    public List<TodoVO> findAll() {
        return todoRepository.findAll().stream()
                .map(this::composeToViewObject)
                .collect(Collectors.toList());
    }

    @Override
    public String createTodo(UserModel userId, String title, String description, MultipartFile cover) {
        String path = saveImage(cover);

        TodoModel todoModel = new TodoModel();
        todoModel.setId(UUID.randomUUID().toString());
        todoModel.setUserId(userId);
        todoModel.setTitle(title);
        todoModel.setDescription(description);
        todoModel.setCover(path);
        todoModel.setIs_finished("Belum Selesai");
        todoModel.setCreatedBy(userId.getUserId());
        todoModel.setModifiedBy(userId.getUserId());

        TodoMessageProducer todoMessageProducer = new TodoMessageProducer();
        todoMessageProducer.setUserId(userId.getUserId());
        todoMessageProducer.setTitle(todoModel.getTitle());
        todoMessageProducer.setDescription(todoModel.getDescription());

        todoMessage.publish(todoMessageProducer);
        todoRepository.save(todoModel);


        return todoModel.getId();
    }

    @Override
    public void isFinished(String userId, String id, String isFinished) {
        if(id == null){
            throw new IllegalArgumentException("The given id must not be null");
        }
        Optional<TodoModel> todoModelOptional = todoRepository.findById(id);
        if(todoModelOptional.isEmpty()){
            throw  new IllegalArgumentException("Todo Not Found");
        }

        TodoModel todoModel = todoModelOptional.get();
        todoModel.setIs_finished(isFinished);
        todoModel.setModifiedBy(userId);

        todoRepository.save(todoModel);
    }

    @Override
    public void deleteTodo(String id) {
        todoRepository.deleteById(id);
    }
    @Autowired
    public void setTodoMessage(TodoMessage todoMessage) {
        this.todoMessage = todoMessage;
    }
}
