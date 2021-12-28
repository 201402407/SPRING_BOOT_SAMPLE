DELETE FROM members;
DELETE FROM documents;
DELETE FROM approvals;

INSERT INTO members
	(member_id, pwd, name, created_at, updated_at)
VALUES
    ('root', '1234', '관리자', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('root1', '1234', '관리자', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('root2', '1234', '관리자', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('root3', '1234', '관리자', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('banner4', '1234', '이해원', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
	
INSERT INTO documents
	(document_id, registered_member_id, document_status, document_type, title, comment, opinion, created_at, updated_at)
VALUES
	(1, 'root', 'PROGRESS', 'COST_SETTLEMENT', '제목입니다', '내용입니다.', null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'root1', 'PROGRESS', 'VACATION', 'ROOT1', '내용입니다.', null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'root2', 'PROGRESS', 'VACATION', 'ROOT2', '내용입니다.', null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 'root3', 'PROGRESS', 'VACATION', 'ROOT3', '내용입니다.', null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, 'banner4', 'PROGRESS', 'VACATION', 'BANNER1', '내용입니다.', null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (6, 'banner4', 'PROGRESS', 'VACATION', 'BANNER2', '내용입니다.', null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (7, 'banner4', 'PROGRESS', 'VACATION', 'BANNER3', '내용입니다.', null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO approvals
    (approval_id, document_id, member_id, seq, approval_status, created_at, updated_at)
VALUES
    (1, 5, 'root', 1, 'WAIT', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 1, 'root', 1, 'WAIT', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (6, 2, 'root', 1, 'WAIT', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (7, 3, 'root', 1, 'APPROVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

