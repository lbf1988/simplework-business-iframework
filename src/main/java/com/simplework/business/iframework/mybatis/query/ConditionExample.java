package com.simplework.business.iframework.mybatis.query;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Brant Liu
 * @Desc TODO
 * @MailTo lbf1988@qq.com
 * @Date 2017/8/14
 */
public class ConditionExample {
    protected Integer limit;

    protected Integer offset;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ConditionExample() {
        this(null,null);
    }

    public ConditionExample(Integer limit, Integer offset) {
        this(limit,offset,null);
    }

    public ConditionExample(Integer limit, Integer offset, String orderByClause) {
        this(limit,offset,orderByClause,false);
    }

    public ConditionExample(Integer limit, Integer offset, String orderByClause, boolean distinct) {
        this.limit = limit;
        this.offset = offset;
        this.orderByClause = orderByClause;
        this.distinct = distinct;
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 获取空的条件
     * @return
     */
    public static ConditionExample getNullExample(){
        return new ConditionExample();
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria){
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
        limit = null;
        offset = null;
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

        public Criteria andIsNull(String column) {
            addCriterion(column +" is null");
            return (Criteria) this;
        }

        public Criteria andIsNotNull(String column) {
            addCriterion(column +" is not null");
            return (Criteria) this;
        }

        public Criteria andEqualTo(String column, Object value) {
            addCriterion(column+" =", value, column);
            return (Criteria) this;
        }

        public Criteria andNotEqualTo(String column, Object value) {
            addCriterion(column+" <>", value, column);
            return (Criteria) this;
        }

        public Criteria andGreaterThan(String column, Object value) {
            addCriterion(column+" >", value, column);
            return (Criteria) this;
        }

        public Criteria andGreaterThanOrEqualTo(String column, Object value) {
            addCriterion(column+" >=", value, column);
            return (Criteria) this;
        }

        public Criteria andLessThan(String column, Object value) {
            addCriterion(column+" <", value, column);
            return (Criteria) this;
        }

        public Criteria andLessThanOrEqualTo(String column, Object value) {
            addCriterion(column+" <=", value, column);
            return (Criteria) this;
        }

        public Criteria andLike(String column,String value) {
            addCriterion(column+" like", value, column);
            return (Criteria) this;
        }

        public Criteria andNotLike(String column,String value) {
            addCriterion(column+" not like", value, column);
            return (Criteria) this;
        }

        public Criteria andIn(String column, List<Object> values) {
            addCriterion(column+" in", values, column);
            return (Criteria) this;
        }

        public Criteria andNotIn(String column, List<Object> values) {
            addCriterion(column+" not in", values, column);
            return (Criteria) this;
        }

        public Criteria andBetween(String column, Object value1, Object value2) {
            addCriterion(column+" between", value1, value2, column);
            return (Criteria) this;
        }

        public Criteria andNotBetween(String column, Object value1, Object value2) {
            addCriterion(column+" not between", value1, value2, column);
            return (Criteria) this;
        }
    }

    public class Criteria extends GeneratedCriteria {
        public Criteria() {
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
