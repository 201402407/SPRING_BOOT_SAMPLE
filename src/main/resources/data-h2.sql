DELETE FROM members;
DELETE FROM documents;
DELETE FROM approvals;

INSERT INTO members
	(member_id, pwd, name, created_at, updated_at)
VALUES
	('root', '1234', '관리자', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
	('leehaewon', 'leehaewon', '이해원', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
	('temp', 'temp', '이해투', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
	('temp2', 'temp2', '이해삼', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
	('temp3', 'temp3', '이해포', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
	('banner4', '12607F63E7EBCA98C9C6F99FAE2AAC6B757718281106105DFA6754D097AE2476', '이해원', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
	
INSERT INTO documents
	(document_id, registered_member_id, document_status, document_type, title, comment, opinion, created_at, updated_at)
VALUES
	(1, 'root', 'PROGRESS', 'COST_SETTLEMENT', '제목입니다', '내용입니다.', null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'banner4', 'PROGRESS', 'VACATION', '제목입니다2', '내용입니다.', '의견입니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'leehaewon', 'APPROVE', 'VACATION', '제목입니다2', '내용입니다.', '의견입니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 'banner4', 'PROGRESS', 'COOPERATION', '문서 아이디 4인 문서의 제목입니다', '문서 아이디 4인 문서의 내용입니다.', null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, 'root', 'PROGRESS', 'VACATION', '제목입니다요', '내용입니다.', null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO approvals
    (approval_id, document_id, member_id, seq, approval_status, created_at, updated_at)
VALUES
    (1, 5, 'root', 1, 'WAIT', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 5, 'leehaewon', 2, 'WAIT', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 5, 'temp', 3, 'WAIT', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 1, 'root', 1, 'WAIT', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, 1, 'leehaewon', 2, 'WAIT', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (6, 2, 'root', 1, 'WAIT', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (7, 3, 'root', 1, 'APPROVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

