package com.wangzhixuan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.wangzhixuan.mapper.OrganizationMapper;
import com.wangzhixuan.model.Organization;
import com.wangzhixuan.service.OrganizationService;
import com.wangzhixuan.vo.Tree;
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public List<Tree> findTree() {
        List<Tree> trees = Lists.newArrayList();

        List<Organization> organizationFather = organizationMapper.findOrganizationAllByPidNull();

        if(organizationFather != null) {
            for (Organization organizationOne : organizationFather) {
                Tree treeOne = new Tree();

                treeOne.setId(organizationOne.getId().toString());
                treeOne.setText(organizationOne.getName());
                treeOne.setIconCls(organizationOne.getIcon());

                List<Organization> organizationSon = organizationMapper.findOrganizationAllByPid(organizationOne.getId());

                if(organizationSon != null) {
                    List<Tree> tree = Lists.newArrayList();
                    for (Organization organizationTwo : organizationSon) {
                        Tree treeTwo = new Tree();
                        treeTwo.setId(organizationTwo.getId().toString());
                        treeTwo.setText(organizationTwo.getName());
                        treeTwo.setIconCls(organizationTwo.getIcon());
                        tree.add(treeTwo);
                    }
                    treeOne.setChildren(tree);
                }else{
                    treeOne.setState("closed");
                }
                trees.add(treeOne);
            }
        }
        return trees;
    }

    @Override
    public List<Organization> findTreeGrid() {
        return organizationMapper.findOrganizationAll();
    }

    @Override
    public void addOrganization(Organization organization) {
        organizationMapper.insert(organization);
    }

    @Override
    public Organization findOrganizationById(Long id) {
        return organizationMapper.findOrganizationById(id);
    }

}
