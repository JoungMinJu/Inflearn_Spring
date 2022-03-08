package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

// 다른 버전에서도 사용하므로 위치가 여기입니다.

public class ModelView {
    private String viewName;
    // 모델 만들기
    private Map<String,Object> model = new HashMap<>();


    public ModelView(String viewName) {
        this.viewName = viewName;
    }
    // 게터세터
    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
