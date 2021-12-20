package uk.co.caci.cyp.mis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uk.co.caci.cyp.mis.entities.Student;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnAllStudents() throws Exception {
        this.mockMvc.perform(get("/api/student"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThan(2))));
    }

    @Test
    public void shouldReturnStudentById() throws Exception {
        this.mockMvc.perform(get("/api/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.fullName", is("Jane Doe")));
    }

    @Test
    public void shouldReturnAllExamResults() throws Exception {
        this.mockMvc.perform(get("/api/exam-result"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    public void shouldReturnAllExamResultsForStudent() throws Exception {
        this.mockMvc.perform(get("/api/student/1/exam-result"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void shouldReturnAllExamResultGrade() throws Exception {
        this.mockMvc.perform(get("/api/exam-result/1/grade"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.grade", is("B")));

        this.mockMvc.perform(get("/api/exam-result/2/grade"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.grade", is("F")));

        this.mockMvc.perform(get("/api/exam-result/3/grade"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.grade", is("A")));

        this.mockMvc.perform(get("/api/exam-result/4/grade"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.grade", is("U")));

        this.mockMvc.perform(get("/api/exam-result/5/grade"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.grade", is("B")));
    }

    @Test
    public void shouldCreateStudent() throws Exception {
        Student student = new Student();
        student.setFullName("Unit Test");
        MockHttpServletRequestBuilder content = MockMvcRequestBuilders.post("/api/student")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(student));

        mockMvc.perform(content)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName", is("Unit Test")));
    }
}
