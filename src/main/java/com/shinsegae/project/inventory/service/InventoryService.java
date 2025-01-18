package com.shinsegae.project.inventory.service;

import com.shinsegae.project.inventory.mapper.InventoryMapper;
import com.shinsegae.project.inventory.vo.InventoryManagementDTO;
import com.shinsegae.project.inventory.vo.InventoryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InventoryService {
    @Autowired
    private final InventoryMapper inventoryMapper;

    public List<InventoryManagementDTO> selectInventoryAll() {
        return inventoryMapper.selectInventoryAll();
    }

    // 수량 업데이트
    public boolean updateStock(String code, int stock) {
        try {
            inventoryMapper.updateStock(code, stock);  // Mapper의 updateStock 메서드 호출
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int insertInventory(InventoryManagementDTO inventoryManagementDTO) {
        return inventoryMapper.insertInventory(inventoryManagementDTO);
    }

    public boolean deleteInventory(String code) {
        try {
            inventoryMapper.deleteInventory(code);  // Mapper의 deleteInventory 메서드 호출
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}