package com.springboot.demo.auth.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * tree 抽象节点 树可继承这个节点
 *
 * @author Jim
 */
@Getter
@Setter
public abstract class BaseTreeNode {

    protected Integer id;
    protected Integer parentId;
    protected List<BaseTreeNode> children = new ArrayList<>();

    public void addChilren(BaseTreeNode node) {
        this.children.add(node);
    }
}
