--회원 입력 --
insert into member(id,name,pwd,email,phone)
values('one','김나리','1111','abc@naver.com','010-3333-4444');
--관리자 입력--
insert into member(id,name,pwd,email,phone,adminyn)
values('admin','관리자','1111','def@naver.com','010-5555-6666','Y');

select * from member;
delete from member;