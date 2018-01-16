package com.upload.v3.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImgResourcesExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Limit limit;

    private static final long serialVersionUID = 1L;

    public ImgResourcesExample() {
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

    public void setLimit(Limit limit) {
        this.limit = limit;
    }

    public Limit getLimit() {
        return this.limit;
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

        public Criteria andTypeIsNull() {
            addCriterion("_type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("_type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("_type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("_type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("_type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("_type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("_type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("_type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("_type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("_type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("_type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("_type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("_type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("_type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andGidIsNull() {
            addCriterion("_gid is null");
            return (Criteria) this;
        }

        public Criteria andGidIsNotNull() {
            addCriterion("_gid is not null");
            return (Criteria) this;
        }

        public Criteria andGidEqualTo(String value) {
            addCriterion("_gid =", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotEqualTo(String value) {
            addCriterion("_gid <>", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidGreaterThan(String value) {
            addCriterion("_gid >", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidGreaterThanOrEqualTo(String value) {
            addCriterion("_gid >=", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidLessThan(String value) {
            addCriterion("_gid <", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidLessThanOrEqualTo(String value) {
            addCriterion("_gid <=", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidLike(String value) {
            addCriterion("_gid like", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotLike(String value) {
            addCriterion("_gid not like", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidIn(List<String> values) {
            addCriterion("_gid in", values, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotIn(List<String> values) {
            addCriterion("_gid not in", values, "gid");
            return (Criteria) this;
        }

        public Criteria andGidBetween(String value1, String value2) {
            addCriterion("_gid between", value1, value2, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotBetween(String value1, String value2) {
            addCriterion("_gid not between", value1, value2, "gid");
            return (Criteria) this;
        }

        public Criteria andImgIsNull() {
            addCriterion("_img is null");
            return (Criteria) this;
        }

        public Criteria andImgIsNotNull() {
            addCriterion("_img is not null");
            return (Criteria) this;
        }

        public Criteria andImgEqualTo(String value) {
            addCriterion("_img =", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotEqualTo(String value) {
            addCriterion("_img <>", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThan(String value) {
            addCriterion("_img >", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThanOrEqualTo(String value) {
            addCriterion("_img >=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThan(String value) {
            addCriterion("_img <", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThanOrEqualTo(String value) {
            addCriterion("_img <=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLike(String value) {
            addCriterion("_img like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotLike(String value) {
            addCriterion("_img not like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgIn(List<String> values) {
            addCriterion("_img in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotIn(List<String> values) {
            addCriterion("_img not in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgBetween(String value1, String value2) {
            addCriterion("_img between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotBetween(String value1, String value2) {
            addCriterion("_img not between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("_status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("_status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("_status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("_status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("_status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("_status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("_status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("_status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("_status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("_status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("_status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("_status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("_status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("_status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("_create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("_create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("_create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("_create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("_create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("_create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("_create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("_create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("_create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("_create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("_create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andChildTypeIsNull() {
            addCriterion("_child_type is null");
            return (Criteria) this;
        }

        public Criteria andChildTypeIsNotNull() {
            addCriterion("_child_type is not null");
            return (Criteria) this;
        }

        public Criteria andChildTypeEqualTo(String value) {
            addCriterion("_child_type =", value, "childType");
            return (Criteria) this;
        }

        public Criteria andChildTypeNotEqualTo(String value) {
            addCriterion("_child_type <>", value, "childType");
            return (Criteria) this;
        }

        public Criteria andChildTypeGreaterThan(String value) {
            addCriterion("_child_type >", value, "childType");
            return (Criteria) this;
        }

        public Criteria andChildTypeGreaterThanOrEqualTo(String value) {
            addCriterion("_child_type >=", value, "childType");
            return (Criteria) this;
        }

        public Criteria andChildTypeLessThan(String value) {
            addCriterion("_child_type <", value, "childType");
            return (Criteria) this;
        }

        public Criteria andChildTypeLessThanOrEqualTo(String value) {
            addCriterion("_child_type <=", value, "childType");
            return (Criteria) this;
        }

        public Criteria andChildTypeLike(String value) {
            addCriterion("_child_type like", value, "childType");
            return (Criteria) this;
        }

        public Criteria andChildTypeNotLike(String value) {
            addCriterion("_child_type not like", value, "childType");
            return (Criteria) this;
        }

        public Criteria andChildTypeIn(List<String> values) {
            addCriterion("_child_type in", values, "childType");
            return (Criteria) this;
        }

        public Criteria andChildTypeNotIn(List<String> values) {
            addCriterion("_child_type not in", values, "childType");
            return (Criteria) this;
        }

        public Criteria andChildTypeBetween(String value1, String value2) {
            addCriterion("_child_type between", value1, value2, "childType");
            return (Criteria) this;
        }

        public Criteria andChildTypeNotBetween(String value1, String value2) {
            addCriterion("_child_type not between", value1, value2, "childType");
            return (Criteria) this;
        }

        public Criteria andAccIdIsNull() {
            addCriterion("_acc_id is null");
            return (Criteria) this;
        }

        public Criteria andAccIdIsNotNull() {
            addCriterion("_acc_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccIdEqualTo(String value) {
            addCriterion("_acc_id =", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdNotEqualTo(String value) {
            addCriterion("_acc_id <>", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdGreaterThan(String value) {
            addCriterion("_acc_id >", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdGreaterThanOrEqualTo(String value) {
            addCriterion("_acc_id >=", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdLessThan(String value) {
            addCriterion("_acc_id <", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdLessThanOrEqualTo(String value) {
            addCriterion("_acc_id <=", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdLike(String value) {
            addCriterion("_acc_id like", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdNotLike(String value) {
            addCriterion("_acc_id not like", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdIn(List<String> values) {
            addCriterion("_acc_id in", values, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdNotIn(List<String> values) {
            addCriterion("_acc_id not in", values, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdBetween(String value1, String value2) {
            addCriterion("_acc_id between", value1, value2, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdNotBetween(String value1, String value2) {
            addCriterion("_acc_id not between", value1, value2, "accId");
            return (Criteria) this;
        }

        public Criteria andAccNameIsNull() {
            addCriterion("_acc_name is null");
            return (Criteria) this;
        }

        public Criteria andAccNameIsNotNull() {
            addCriterion("_acc_name is not null");
            return (Criteria) this;
        }

        public Criteria andAccNameEqualTo(String value) {
            addCriterion("_acc_name =", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameNotEqualTo(String value) {
            addCriterion("_acc_name <>", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameGreaterThan(String value) {
            addCriterion("_acc_name >", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameGreaterThanOrEqualTo(String value) {
            addCriterion("_acc_name >=", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameLessThan(String value) {
            addCriterion("_acc_name <", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameLessThanOrEqualTo(String value) {
            addCriterion("_acc_name <=", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameLike(String value) {
            addCriterion("_acc_name like", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameNotLike(String value) {
            addCriterion("_acc_name not like", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameIn(List<String> values) {
            addCriterion("_acc_name in", values, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameNotIn(List<String> values) {
            addCriterion("_acc_name not in", values, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameBetween(String value1, String value2) {
            addCriterion("_acc_name between", value1, value2, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameNotBetween(String value1, String value2) {
            addCriterion("_acc_name not between", value1, value2, "accName");
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