package com.itany.netClass.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CourseExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNull() {
            addCriterion("course_name is null");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNotNull() {
            addCriterion("course_name is not null");
            return (Criteria) this;
        }

        public Criteria andCourseNameEqualTo(String value) {
            addCriterion("course_name =", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotEqualTo(String value) {
            addCriterion("course_name <>", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThan(String value) {
            addCriterion("course_name >", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThanOrEqualTo(String value) {
            addCriterion("course_name >=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThan(String value) {
            addCriterion("course_name <", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThanOrEqualTo(String value) {
            addCriterion("course_name <=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLike(String value) {
            addCriterion("course_name like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotLike(String value) {
            addCriterion("course_name not like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameIn(List<String> values) {
            addCriterion("course_name in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotIn(List<String> values) {
            addCriterion("course_name not in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameBetween(String value1, String value2) {
            addCriterion("course_name between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotBetween(String value1, String value2) {
            addCriterion("course_name not between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseInfoIsNull() {
            addCriterion("course_info is null");
            return (Criteria) this;
        }

        public Criteria andCourseInfoIsNotNull() {
            addCriterion("course_info is not null");
            return (Criteria) this;
        }

        public Criteria andCourseInfoEqualTo(String value) {
            addCriterion("course_info =", value, "courseInfo");
            return (Criteria) this;
        }

        public Criteria andCourseInfoNotEqualTo(String value) {
            addCriterion("course_info <>", value, "courseInfo");
            return (Criteria) this;
        }

        public Criteria andCourseInfoGreaterThan(String value) {
            addCriterion("course_info >", value, "courseInfo");
            return (Criteria) this;
        }

        public Criteria andCourseInfoGreaterThanOrEqualTo(String value) {
            addCriterion("course_info >=", value, "courseInfo");
            return (Criteria) this;
        }

        public Criteria andCourseInfoLessThan(String value) {
            addCriterion("course_info <", value, "courseInfo");
            return (Criteria) this;
        }

        public Criteria andCourseInfoLessThanOrEqualTo(String value) {
            addCriterion("course_info <=", value, "courseInfo");
            return (Criteria) this;
        }

        public Criteria andCourseInfoLike(String value) {
            addCriterion("course_info like", value, "courseInfo");
            return (Criteria) this;
        }

        public Criteria andCourseInfoNotLike(String value) {
            addCriterion("course_info not like", value, "courseInfo");
            return (Criteria) this;
        }

        public Criteria andCourseInfoIn(List<String> values) {
            addCriterion("course_info in", values, "courseInfo");
            return (Criteria) this;
        }

        public Criteria andCourseInfoNotIn(List<String> values) {
            addCriterion("course_info not in", values, "courseInfo");
            return (Criteria) this;
        }

        public Criteria andCourseInfoBetween(String value1, String value2) {
            addCriterion("course_info between", value1, value2, "courseInfo");
            return (Criteria) this;
        }

        public Criteria andCourseInfoNotBetween(String value1, String value2) {
            addCriterion("course_info not between", value1, value2, "courseInfo");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andCoverImageUrlIsNull() {
            addCriterion("cover_image_url is null");
            return (Criteria) this;
        }

        public Criteria andCoverImageUrlIsNotNull() {
            addCriterion("cover_image_url is not null");
            return (Criteria) this;
        }

        public Criteria andCoverImageUrlEqualTo(String value) {
            addCriterion("cover_image_url =", value, "coverImageUrl");
            return (Criteria) this;
        }

        public Criteria andCoverImageUrlNotEqualTo(String value) {
            addCriterion("cover_image_url <>", value, "coverImageUrl");
            return (Criteria) this;
        }

        public Criteria andCoverImageUrlGreaterThan(String value) {
            addCriterion("cover_image_url >", value, "coverImageUrl");
            return (Criteria) this;
        }

        public Criteria andCoverImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("cover_image_url >=", value, "coverImageUrl");
            return (Criteria) this;
        }

        public Criteria andCoverImageUrlLessThan(String value) {
            addCriterion("cover_image_url <", value, "coverImageUrl");
            return (Criteria) this;
        }

        public Criteria andCoverImageUrlLessThanOrEqualTo(String value) {
            addCriterion("cover_image_url <=", value, "coverImageUrl");
            return (Criteria) this;
        }

        public Criteria andCoverImageUrlLike(String value) {
            addCriterion("cover_image_url like", value, "coverImageUrl");
            return (Criteria) this;
        }

        public Criteria andCoverImageUrlNotLike(String value) {
            addCriterion("cover_image_url not like", value, "coverImageUrl");
            return (Criteria) this;
        }

        public Criteria andCoverImageUrlIn(List<String> values) {
            addCriterion("cover_image_url in", values, "coverImageUrl");
            return (Criteria) this;
        }

        public Criteria andCoverImageUrlNotIn(List<String> values) {
            addCriterion("cover_image_url not in", values, "coverImageUrl");
            return (Criteria) this;
        }

        public Criteria andCoverImageUrlBetween(String value1, String value2) {
            addCriterion("cover_image_url between", value1, value2, "coverImageUrl");
            return (Criteria) this;
        }

        public Criteria andCoverImageUrlNotBetween(String value1, String value2) {
            addCriterion("cover_image_url not between", value1, value2, "coverImageUrl");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andClickNumberIsNull() {
            addCriterion("click_number is null");
            return (Criteria) this;
        }

        public Criteria andClickNumberIsNotNull() {
            addCriterion("click_number is not null");
            return (Criteria) this;
        }

        public Criteria andClickNumberEqualTo(Integer value) {
            addCriterion("click_number =", value, "clickNumber");
            return (Criteria) this;
        }

        public Criteria andClickNumberNotEqualTo(Integer value) {
            addCriterion("click_number <>", value, "clickNumber");
            return (Criteria) this;
        }

        public Criteria andClickNumberGreaterThan(Integer value) {
            addCriterion("click_number >", value, "clickNumber");
            return (Criteria) this;
        }

        public Criteria andClickNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("click_number >=", value, "clickNumber");
            return (Criteria) this;
        }

        public Criteria andClickNumberLessThan(Integer value) {
            addCriterion("click_number <", value, "clickNumber");
            return (Criteria) this;
        }

        public Criteria andClickNumberLessThanOrEqualTo(Integer value) {
            addCriterion("click_number <=", value, "clickNumber");
            return (Criteria) this;
        }

        public Criteria andClickNumberIn(List<Integer> values) {
            addCriterion("click_number in", values, "clickNumber");
            return (Criteria) this;
        }

        public Criteria andClickNumberNotIn(List<Integer> values) {
            addCriterion("click_number not in", values, "clickNumber");
            return (Criteria) this;
        }

        public Criteria andClickNumberBetween(Integer value1, Integer value2) {
            addCriterion("click_number between", value1, value2, "clickNumber");
            return (Criteria) this;
        }

        public Criteria andClickNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("click_number not between", value1, value2, "clickNumber");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andRecommendationGradeIsNull() {
            addCriterion("recommendation_grade is null");
            return (Criteria) this;
        }

        public Criteria andRecommendationGradeIsNotNull() {
            addCriterion("recommendation_grade is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendationGradeEqualTo(Integer value) {
            addCriterion("recommendation_grade =", value, "recommendationGrade");
            return (Criteria) this;
        }

        public Criteria andRecommendationGradeNotEqualTo(Integer value) {
            addCriterion("recommendation_grade <>", value, "recommendationGrade");
            return (Criteria) this;
        }

        public Criteria andRecommendationGradeGreaterThan(Integer value) {
            addCriterion("recommendation_grade >", value, "recommendationGrade");
            return (Criteria) this;
        }

        public Criteria andRecommendationGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("recommendation_grade >=", value, "recommendationGrade");
            return (Criteria) this;
        }

        public Criteria andRecommendationGradeLessThan(Integer value) {
            addCriterion("recommendation_grade <", value, "recommendationGrade");
            return (Criteria) this;
        }

        public Criteria andRecommendationGradeLessThanOrEqualTo(Integer value) {
            addCriterion("recommendation_grade <=", value, "recommendationGrade");
            return (Criteria) this;
        }

        public Criteria andRecommendationGradeIn(List<Integer> values) {
            addCriterion("recommendation_grade in", values, "recommendationGrade");
            return (Criteria) this;
        }

        public Criteria andRecommendationGradeNotIn(List<Integer> values) {
            addCriterion("recommendation_grade not in", values, "recommendationGrade");
            return (Criteria) this;
        }

        public Criteria andRecommendationGradeBetween(Integer value1, Integer value2) {
            addCriterion("recommendation_grade between", value1, value2, "recommendationGrade");
            return (Criteria) this;
        }

        public Criteria andRecommendationGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("recommendation_grade not between", value1, value2, "recommendationGrade");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdIsNull() {
            addCriterion("course_type_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdIsNotNull() {
            addCriterion("course_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdEqualTo(Integer value) {
            addCriterion("course_type_id =", value, "courseTypeId");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdNotEqualTo(Integer value) {
            addCriterion("course_type_id <>", value, "courseTypeId");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdGreaterThan(Integer value) {
            addCriterion("course_type_id >", value, "courseTypeId");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("course_type_id >=", value, "courseTypeId");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdLessThan(Integer value) {
            addCriterion("course_type_id <", value, "courseTypeId");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("course_type_id <=", value, "courseTypeId");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdIn(List<Integer> values) {
            addCriterion("course_type_id in", values, "courseTypeId");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdNotIn(List<Integer> values) {
            addCriterion("course_type_id not in", values, "courseTypeId");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("course_type_id between", value1, value2, "courseTypeId");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("course_type_id not between", value1, value2, "courseTypeId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}