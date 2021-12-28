package com.example.test.documentapproval;

import com.example.test.TestApplicationTests;
import com.example.test.documentapproval.constants.DocumentStatus;
import com.example.test.documentapproval.constants.DocumentType;
import com.example.test.documentapproval.document.DocumentRepository;
import com.example.test.documentapproval.entities.Document;
import com.example.test.documentapproval.entities.Member;
import com.example.test.documentapproval.repository.MemberRepository;
import com.example.test.documentapproval.service.DocumentService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class DocumentApprovalTests extends TestApplicationTests {
    public DocumentApprovalTests() {
        super("/api/documents");
    }

    @Autowired
    private DocumentRepository documentRepository;

    @Nested
    @DisplayName("문서 생성")
    class Create {
        JSONObject createData() throws JSONException {
            JSONArray approvalMemberIdList = new JSONArray()
                    .put("root")
                    .put("leehaewon");

            JSONObject data = new JSONObject()
                    .put("memberId", "root")
                    .put("documentType", DocumentType.COST_SETTLEMENT)
                    .put("title", "문서 제목 입니다!")
                    .put("comment", "내용입니다!")
                    .put("approvalMemberIdList", approvalMemberIdList);

            return data;
        }

        @Test
        @DisplayName("실패 - 문서 분류 정의된 값 아닌 경우")
        void 문서_분류_미정의값_요청() throws Exception {
            JSONObject data = createData();
            data.put("documentType", "미존재값");

            failure(reqPost("/", data));
        }

        @Test
        @DisplayName("성공")
        void successTest() throws Exception {
            JSONObject data = createData();

            success(reqPost("/", data))
//                    .andExpect(jsonPath("$.response.documentId", is("6")))
                    .andExpect(jsonPath("$.response.registeredMember.memberId", is("root")))
                    .andExpect(jsonPath("$.response.documentStatus", is(DocumentStatus.PROGRESS.getCode())))
                    .andExpect(jsonPath("$.response.documentType", is(DocumentType.COST_SETTLEMENT.getCode())))
                    .andExpect(jsonPath("$.response.title", is("문서 제목 입니다!")))
                    .andExpect(jsonPath("$.response.comment", is("내용입니다!")));
        }
    }

    @Transactional
    @Test
    void DOCUMENT_전체_조회시_N1_문제_발생_확인() {
        List<Document> documentList = documentRepository.findAll();
    }
}
