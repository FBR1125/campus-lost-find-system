package com.example.lostandfound.service;

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
        // 1. 获取认领信息
        var claim = claimMapper.getById(claimId);
        if (claim == null) {
            throw new RuntimeException("记录不存在");
        }

        // 2. 删除认领记录
        claimMapper.deleteById(claimId);

        // 3. 把物品状态恢复为 待认领（0）
        itemMapper.updateStatus(claim.getItemId(), 0);
    }
}