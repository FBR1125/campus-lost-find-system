package com.example.lostandfound.service;

import com.example.lostandfound.entity.Claim;
import com.example.lostandfound.mapper.ClaimMapper;
import com.example.lostandfound.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClaimService {

    @Autowired
    private ClaimMapper claimMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Transactional
    public void cancelClaim(Integer claimId) {

        // 1. 获取认领记录
        Claim claim = claimMapper.getById(claimId);
        if (claim == null) {
            throw new RuntimeException("认领记录不存在");
        }

        // 2. 拿到 物品 ID
        Integer itemId = claim.getItemId();

        // 3. 删除认领记录
        claimMapper.deleteById(claimId);

        // 4. 把物品状态改回 0（待认领）
        itemMapper.updateStatus(itemId, 0);
    }
}