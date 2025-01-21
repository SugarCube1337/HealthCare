package se.ifmo.healthcare.controllers;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.ifmo.healthcare.auth.JWTUtil;
import se.ifmo.healthcare.dto.StaffMemberDTO;
import se.ifmo.healthcare.services.StaffMemberService;

import java.util.List;

@Controller
@RequestMapping("/staff-members")
public class StaffMemberController {

    @Autowired
    private StaffMemberService staffMemberService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/register_staff")
    public String showRegisterPage(Model model) {
        model.addAttribute("staffMember", new StaffMemberDTO());
        return "register_staff";
    }

    @PostMapping("/register_staff")
    public String registerStaff(@ModelAttribute StaffMemberDTO StaffMemberDTO) {
        staffMemberService.createStaffMember(StaffMemberDTO);
        return "redirect:/auth/login";
    }
    @GetMapping("/staff_dashboard")
    public String showPatientDashboard(Model model, HttpSession session) {
        String token = (String) session.getAttribute("jwtToken");
        System.out.println(token);
        if (token == null) {
            return "redirect:/auth/login";
        }

        Claims claims = jwtUtil.extractAllClaims(token);
        System.out.println(claims);
        String role = claims.get("role", String.class);
        Long id = claims.get("id", Long.class);

        System.out.println(id);
        System.out.println(role);
        if (!"STAFF".equals(role)) {
            return "redirect:/auth/login";
        }

        StaffMemberDTO StaffMemberDTO = staffMemberService.getStaffMemberByPersonId(id);
        System.out.println(StaffMemberDTO);
        if (StaffMemberDTO == null) {
            return "redirect:/auth/login";
        }

        model.addAttribute("staffMember", StaffMemberDTO);


        return "staff_member_dashboard";
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
