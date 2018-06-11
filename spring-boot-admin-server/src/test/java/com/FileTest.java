package com;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 描述:
 *
 * @Author LJL
 * @Date 2018/5/24 0024 13:55
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FileTest {
        private MockMvc mockMvc;

        @Autowired
        private WebApplicationContext context;

        @Before
        public void before() {
            //独立安装测试
            //mockMvc = MockMvcBuilders.standaloneSetup(new IndexController()).build();
            //集成Web环境测试（此种方式并不会集成真正的web环境，而是通过相应的Mock API进行模拟测试，无须启动服务器）
            mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        }

        /**
         * 招商项目项目状态数量统计信息
         * @throws Exception
         */
        @Test
        public void getProjectAnalysisChartVo() throws  Exception {

            this.mockMvc.perform(MockMvcRequestBuilders.get("/api/districtCompany/downCompanyExcelModel")
            )
                    .andDo(MockMvcResultHandlers.print());
        }
}
