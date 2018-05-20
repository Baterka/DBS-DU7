/*
 Navicat Premium Data Transfer

 Source Server         : CVUT PostgreSQL
 Source Server Type    : PostgreSQL
 Source Server Version : 90311
 Source Host           : slon.felk.cvut.cz:5432
 Source Catalog        : db18_bubenda1
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 90311
 File Encoding         : 65001

 Date: 20/05/2018 18:13:39
*/


-- ----------------------------
-- Table structure for java_persons
-- ----------------------------
DROP TABLE IF EXISTS "public"."java_persons";
CREATE TABLE "public"."java_persons" (
  "id" int4 NOT NULL DEFAULT nextval('java_persons_id_seq'::regclass),
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "surname" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "birthdate" date NOT NULL,
  "address" varchar(255) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Uniques structure for table java_persons
-- ----------------------------
ALTER TABLE "public"."java_persons" ADD CONSTRAINT "person_id" UNIQUE ("id");

-- ----------------------------
-- Primary Key structure for table java_persons
-- ----------------------------
ALTER TABLE "public"."java_persons" ADD CONSTRAINT "java_persons_pkey" PRIMARY KEY ("id");
