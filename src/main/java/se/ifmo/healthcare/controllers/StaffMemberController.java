package se.ifmo.healthcare.controllers;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.ifmo.healthcare.auth.JWTUtil;
import se.ifmo.healthcare.dto.PatientDTO;
import se.ifmo.healthcare.dto.StaffMemberDTO;
import se.ifmo.healthcare.services.StaffMemberService;

import java.util.List;

@RestController
@RequestMapping("/staff-members")
public class StaffMemberController {

    @Autowired
    private StaffMemberService staffMemberService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/register_staff")
    public String showRegisterPage(Model model) {
        model.addAttribute("staff_member", new StaffMemberDTO());
        return "register_staff";
    }

    @PostMapping("/register_staff")
    public String registerStaff(@ModelAttribute StaffMemberDTO StaffMemberDTO) {
        staffMemberService.createStaffMember(StaffMemberDTO);
        return "redirect:/staff-members/staff_member_dahboard";
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
        String username = claims.get("username", String.class);
        String role = claims.get("role", String.class);
        Long id = claims.get("id", Long.class);

        System.out.println(id);

        if (!"STAFF".equals(role)) {
            return "redirect:/auth/login";
        }

        StaffMemberDTO StaffMemberDTO = staffMemberService.getStaffMemberById(id);
        if (StaffMemberDTO == null) {
            return "redirect:/auth/login";
        }

        model.addAttribute("staff_member", StaffMemberDTO);


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
