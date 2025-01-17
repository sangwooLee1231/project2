package com.shinsegae.project.inventory.controller;

import com.shinsegae.project.inventory.service.InventoryService;
import com.shinsegae.project.inventory.vo.InventoryManagementDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("admin/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("confirm_inventory_list")
    public String confirm_inventory_list(Model model) {

        List<InventoryManagementDTO> list = inventoryService.selectInventoryAll();
        Map<String, Integer> sumList = inventoryService.getSum(list);
        model.addAttribute("confirm_inventory_list", inventoryService.selectInventoryAll());
        model.addAttribute("sumList", sumList);
        //모델로 전달(sumList)
        //${sumList.sum1} 출력
        return "admin/inventory/confirm_inventory_list";
    }

    @GetMapping("edit_inventory")
    public String edit_inventory() {
        return "admin/inventory/edit_inventory";
    }

    @DeleteMapping("delete/{code}")
    @ResponseBody
    public boolean deleteInventory(@PathVariable("code") String code) {
        System.out.println(code);
        boolean result = inventoryService.deleteInventory(code);  // 삭제 서비스 호출
    return result;
   }

    // 수량 업데이트 API
    @PutMapping("/update/{code}")
    public ResponseEntity<Boolean> updateStock(@PathVariable("code") String code, @RequestBody Map<String, Integer> request) {
        int stock = request.get("stock");
        boolean result = inventoryService.updateStock(code, stock);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStock(@RequestBody InventoryManagementDTO inventoryManagementDTO) {
        inventoryService.insertInventory(inventoryManagementDTO);
        return ResponseEntity.ok("Stock added successfully");
    }

}