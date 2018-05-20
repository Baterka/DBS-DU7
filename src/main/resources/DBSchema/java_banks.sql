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

 Date: 20/05/2018 18:13:33
*/


-- ----------------------------
-- Table structure for java_banks
-- ----------------------------
DROP TABLE IF EXISTS "public"."java_banks";
CREATE TABLE "public"."java_banks" (
  "id" int4 NOT NULL DEFAULT nextval('java_banks_id_seq'::regclass),
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "codename" varchar(5) COLLATE "pg_catalog"."default" NOT NULL,
  "address" varchar(255) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Uniques structure for table java_banks
-- ----------------------------
ALTER TABLE "public"."java_banks" ADD CONSTRAINT "bank_id" UNIQUE ("id");
ALTER TABLE "public"."java_banks" ADD CONSTRAINT "bank_name" UNIQUE ("name");
ALTER TABLE "public"."java_banks" ADD CONSTRAINT "bank_codename" UNIQUE ("codename");

-- ----------------------------
-- Primary Key structure for table java_banks
-- ----------------------------
ALTER TABLE "public"."java_banks" ADD CONSTRAINT "java_banks_pkey" PRIMARY KEY ("id");
