package morn.library.validate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import morn.library.core.CriteriaMap;
import morn.library.rest.constant.RestMessageConstants;
import morn.library.test.TestUser;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * 分组校验单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GroupValidateTest {

  /**
   * 测试URL
   */
  private static final String BASE_URL = "/test/validate";

  @Autowired
  private MockMvc mvc;

  private CriteriaMap noModel;

  private CriteriaMap noPassword;

  @Before
  public void setUp() {
    noModel = new CriteriaMap();
    noPassword = new CriteriaMap();
    TestUser testUser = new TestUser();
    testUser.setUsername("Timely");
    noPassword.put("model", testUser);
  }

  @Test
  @WithMockUser
  public void add1() throws Exception {
    String content = JSONObject.toJSONString(noModel);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(BASE_URL)
        .content(content).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    mvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("status").value(RestMessageConstants.SUCCESS));
  }

  @Test
  @WithMockUser
  public void add2() throws Exception {
    String content = JSONObject.toJSONString(noPassword);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(BASE_URL)
        .content(content).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    mvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("message").value("model.password must not be null"));
  }

  @Test
  @WithMockUser
  public void update() throws Exception {
    String content = JSONObject.toJSONString(noPassword);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put(BASE_URL)
        .content(content).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    mvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("status").value(RestMessageConstants.SUCCESS));
  }
}
