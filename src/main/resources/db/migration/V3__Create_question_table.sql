create table question
(
	id int auto_increment,
	title varchar(50),
	description text,
	gmt_created bigint,
	gmt_modified bigint,
	author int,
	coment_count int default 0,
	view_count int default 0,
	"like" int default 0,
	tag varchar(256),
	constraint question_pk
		primary key (id)
);

