package com.example.lostandfound.controller;

import com.example.lostandfound.common.Result;
import com.example.lostandfound.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/claim")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @DeleteMapping("/cancel/{id}")
    public Result<?> cancelClaim(@PathVariable Integer id) {
        try {
            claimService.cancelClaim(id);
            return Result.success("撤销认领成功");
        } catch (Exception e) {
            return Result.error("撤销失败：" + e.getMessage());
        }
    }
}