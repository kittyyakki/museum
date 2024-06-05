CREATE OR REPLACE VIEW member_gallery_view AS
SELECT mseq,
       author as author_id,
       name   as author_name,
       title,
       writedate,
       content,
       readcount,
       image,
       savefilename,
       likecount
FROM member_gallery
         INNER JOIN member ON member_gallery.author = member.id
ORDER BY member_gallery.writedate;


CREATE OR REPLACE VIEW review_view AS
SELECT rseq,
       author as author_id,
       name   as author_name,
       title,
       writedate,
       content
FROM review
         INNER JOIN member ON review.author = member.id
ORDER BY review.writedate;


CREATE OR REPLACE VIEW favorite_view AS
SELECT id AS member_id, artwork.*
FROM favorite_artwork
         INNER JOIN artwork ON favorite_artwork.id = artwork.aseq
ORDER BY favorite_artwork.writedate;
