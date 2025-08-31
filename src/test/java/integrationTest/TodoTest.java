package integrationTest;

import com.example.todolist_practice.dao.JPATodoRepository;
import com.example.todolist_practice.model.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest(classes = com.example.todolist_practice.TodolistPracticeApplication.class)
@AutoConfigureMockMvc
public class TodoTest {
    @Autowired
    private MockMvc client;

    @Autowired
    private JPATodoRepository todoRepository;

    @BeforeEach
    public void setup() {
        todoRepository.deleteAll();

        todoRepository.save(new Todo("Learn Spring Boot"));
        todoRepository.save(new Todo("Write unit tests"));
        todoRepository.save(new Todo("Deploy application"));
        todoRepository.save(new Todo("Code review"));
        todoRepository.save(new Todo("Fix bugs"));
    }

    @Test
    public void should_return_todos_when_get_all_todos() throws Exception {
        // given
        List<Todo> givenTodos = todoRepository.findAll();

        // when
        ResultActions perform = client.perform(MockMvcRequestBuilders.get("/api/v1/todos"));

        // then
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(givenTodos.get(0).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].detail").value(givenTodos.get(0).getDetail()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].status").value(givenTodos.get(0).isStatus()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(givenTodos.get(1).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[2].id").value(givenTodos.get(2).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[3].id").value(givenTodos.get(3).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[4].id").value(givenTodos.get(4).getId()));
    }

    @Test
    public void should_return_todo_when_get_todo_by_id() throws Exception {
        // given
        List<Todo> todos = todoRepository.findAll();
        Todo givenTodo = todos.get(0);

        // when
        ResultActions perform = client.perform(MockMvcRequestBuilders.get("/api/v1/todos/" + givenTodo.getId()));

        // then
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(givenTodo.getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.detail").value(givenTodo.getDetail()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(givenTodo.isStatus()));
    }

    @Test
    public void should_create_todo_when_post_todo() throws Exception {
        // given
        String todoJson = "{\"detail\":\"New todo item\"}";
        int initialCount = todoRepository.findAll().size();

        // when
        ResultActions perform = client.perform(MockMvcRequestBuilders.post("/api/v1/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(todoJson));

        // then
        perform.andExpect(MockMvcResultMatchers.status().isCreated());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.detail").value("New todo item"));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(false));

        List<Todo> todos = todoRepository.findAll();
        assert todos.size() == initialCount + 1;
    }

    @Test
    public void should_delete_todo_when_delete_todo_by_id() throws Exception {
        // given
        List<Todo> todos = todoRepository.findAll();
        Todo todoToDelete = todos.get(0);
        int initialCount = todos.size();

        // when
        ResultActions perform = client.perform(MockMvcRequestBuilders.delete("/api/v1/todos/" + todoToDelete.getId()));

        // then
        perform.andExpect(MockMvcResultMatchers.status().isNoContent());

        List<Todo> remainingTodos = todoRepository.findAll();
        assert remainingTodos.size() == initialCount - 1;
        assert !remainingTodos.contains(todoToDelete);
    }

    @Test
    public void should_update_todo_when_put_todo_by_id() throws Exception {
        // given
        List<Todo> todos = todoRepository.findAll();
        Todo todoToUpdate = todos.get(0);
        String updatedTodoJson = "{\"detail\":\"Updated todo item\",\"status\":true}";

        // when
        ResultActions perform = client.perform(MockMvcRequestBuilders.put("/api/v1/todos/" + todoToUpdate.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedTodoJson));

        // then
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(todoToUpdate.getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.detail").value("Updated todo item"));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true));

        Todo updatedTodo = todoRepository.findById(todoToUpdate.getId()).orElseThrow();
        assert updatedTodo.getDetail().equals("Updated todo item");
        assert updatedTodo.isStatus();
    }
}
