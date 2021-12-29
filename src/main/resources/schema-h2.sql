
-- DROP TABLE IF EXISTS members;
-- DROP TABLE IF EXISTS documents;
-- DROP TABLE IF EXISTS approvals;

CREATE TABLE members (
	member_id	varchar(50) NOT NULL PRIMARY KEY,
	pwd			varchar(64)	NOT NULL,
	name		varchar(50) NOT NULL,
	created_at  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	updated_at  datetime
);

CREATE TABLE documents (
	document_id			int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    registered_member_id	varchar(50) NOT NULL,
	document_status		enum('PROGRESS', 'APPROVE', 'REJECT') NOT NULL,
	document_type		enum('COST_SETTLEMENT', 'VACATION', 'COOPERATION') NOT NULL,
	title               varchar(50) NOT NULL,
	comment				varchar(255) NOT NULL,
	opinion				varchar(255),
	created_at  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	updated_at  datetime,
	FOREIGN KEY (registered_member_id)
	REFERENCES members(member_id) ON UPDATE CASCADE
);

CREATE TABLE approvals (
	approval_id			int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	document_id			int NOT NULL,
	member_id			varchar(50) NOT NULL,
	seq					tinyint(1) NOT NULL,
	approval_status		enum('WAIT', 'APPROVE', 'REJECT') NOT NULL,
	created_at  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	updated_at  datetime,
	FOREIGN KEY (document_id)
	REFERENCES documents(document_id) ON UPDATE CASCADE,
	FOREIGN KEY (member_id)
	REFERENCES members(member_id) ON UPDATE CASCADE
);

CREATE TABLE search_result (
   keyword			    varchar(50) NOT NULL PRIMARY KEY,
   searchCount			int NOT NULL
);

CREATE TABLE temp (
    temp        varchar(50) NOT NULL PRIMARY KEY,
    member_id	varchar(50) NOT NULL
);
