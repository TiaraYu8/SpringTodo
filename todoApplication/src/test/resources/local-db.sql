DROP TABLE IF EXISTS todo;
CREATE TABLE IF NOT EXISTS todo(
    `id` VARCHAR(36),
    `user_id` VARCHAR(255),
    `title` VARCHAR(255),
    `description` TEXT,
    `is_finished` VARCHAR(255),
    `cover` TEXT,
    `created_by` VARCHAR(255),
    `created_date` DATE DEFAULT CURRENT_TIMESTAMP,
    `modified_by` VARCHAR(255),
    `modified_date` DATE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(`id`)
);

insert into todo(`id`, `user_id`,`title`,`description`,`is_finished`, `cover`)
values ('todo1', 'user1','title1','desc1','Belum selesai','cover1' ),
        ('todo2', 'user2','title2','desc2','Belum selesai','cover2' ),
        ('todo3', 'user3','title3','desc3','Belum selesai','cover3' ),
        ('todo4', 'user4','title4','desc4','Belum selesai','cover4' );

