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

 Date: 20/05/2018 18:13:45
*/


-- ----------------------------
-- Table structure for java_persons_banks
-- ----------------------------
DROP TABLE IF EXISTS "public"."java_persons_banks";
CREATE TABLE "public"."java_persons_banks" (
  "id" int4 NOT NULL DEFAULT nextval('java_persons_banks_id_seq'::regclass),
  "person" int4 NOT NULL,
  "bank" int4 NOT NULL,
  "assigned" date NOT NULL DEFAULT ('now'::text)::date
)
;

-- ----------------------------
-- Indexes structure for table java_persons_banks
-- ----------------------------
CREATE UNIQUE INDEX "person_bank_index" ON "public"."java_persons_banks" USING btree (
  "person" "pg_catalog"."int4_ops" ASC NULLS LAST,
  "bank" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table java_persons_banks
-- ----------------------------
ALTER TABLE "public"."java_persons_banks" ADD CONSTRAINT "java_persons_banks_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table java_persons_banks
-- ----------------------------
ALTER TABLE "public"."java_persons_banks" ADD CONSTRAINT "java_persons_banks_bank_fkey" FOREIGN KEY ("bank") REFERENCES "public"."java_banks" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."java_persons_banks" ADD CONSTRAINT "java_persons_banks_person_fkey" FOREIGN KEY ("person") REFERENCES "public"."java_persons" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
