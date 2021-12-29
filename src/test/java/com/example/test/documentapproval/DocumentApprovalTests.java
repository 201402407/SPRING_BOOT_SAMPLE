package com.example.test.documentapproval;

import com.example.test.TestApplicationTests;
import com.example.test.documentapproval.constants.DocumentStatus;
import com.example.test.documentapproval.constants.DocumentType;
import com.example.test.documentapproval.repository.DocumentRepository;
import com.example.test.documentapproval.entities.Document;
import com.example.test.documentapproval.entities.Member;
import com.example.test.documentapproval.repository.MemberRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
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

    @Autowired
    private MemberRepository memberRepository;

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
        System.out.println("전체 문서 데이터는 몇개?? " + documentList.size());
    }

    @Transactional
    @Test
    void DOCUMENT_JOIN_FETCH_전체_조회() {
        List<Document> documentList = documentRepository.findAllWithFetchJoin();
        for(Document document: documentList) {
            System.out.println(document.getRegisteredMember().getMemberId());
        }
    }

    @Transactional
    @Test
    void MEMBER_전체_조회시_N1_문제_발생_확인() {
        List<Member> memberList = memberRepository.findAll();
        for(Member member: memberList) {
            System.out.println(member.getMemberId() + "의 Document의 개수는?? " + member.getDocumentList().size());
        }
    }

    @Transactional
    @Test
    void MEMBER_JOIN_FETCH_전체_조회() {
        List<Member> memberList = memberRepository.findAllWithFetchJoin();
        for(Member member: memberList) {
            System.out.println(member.getMemberId() + "의 Document의 개수는?? " + member.getDocumentList().size());
        }
    }

    @Transactional
    @Test
    void MEMBER_JOIN_FETCH_2개의_엔티티_조회() {
        List<Member> memberList = memberRepository.findAllWithFetchJoin();
        for(Member member: memberList) {
            System.out.println(member.getMemberId() + "의 Document의 개수는?? " + member.getDocumentList().size());
        }
    }
}
