package se.ifmo.healthcare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.ifmo.healthcare.dto.StaffMemberDTO;
import se.ifmo.healthcare.services.StaffMemberService;

import java.util.List;

@RestController
@RequestMapping("/api/staff-members")
public class StaffMemberController {

    @Autowired
    private StaffMemberService staffMemberService;

    @PostMapping
    public ResponseEntity<String> createStaffMember(@RequestBody StaffMemberDTO staffMemberDTO) {
        staffMemberService.createStaffMember(staffMemberDTO);
        return ResponseEntity.ok("Staff member created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffMemberDTO> getStaffMemberById(@PathVariable Long id) {
        StaffMemberDTO staffMemberDTO = staffMemberService.getStaffMemberById(id);
        if (staffMemberDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(staffMemberDTO);
    }

    @GetMapping
    public List<StaffMemberDTO> getAllStaffMembers() {
        return staffMemberService.getAllStaffMembers();
    }

    @PutMapping
    public ResponseEntity<String> updateStaffMember(@RequestBody StaffMemberDTO staffMemberDTO) {
        staffMemberService.updateStaffMember(staffMemberDTO);
        return ResponseEntity.ok("Staff member updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStaffMember(@PathVariable Long id) {
        staffMemberService.deleteStaffMember(id);
        return ResponseEntity.ok("Staff member deleted successfully");
    }
}
