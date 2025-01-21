package se.ifmo.healthcare.controllers;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.ifmo.healthcare.auth.JWTUtil;
import se.ifmo.healthcare.dto.MedicalReportDTO;
import se.ifmo.healthcare.dto.PatientDTO;
import se.ifmo.healthcare.dto.ResearchRegistrationDTO;
import se.ifmo.healthcare.dto.StaffMemberDTO;
import se.ifmo.healthcare.services.MedicalReportService;
import se.ifmo.healthcare.services.ResearchService;
import se.ifmo.healthcare.services.StaffMemberService;

import java.util.List;

@Controller
@RequestMapping("/medical-reports")
public class MedicalReportController {
    @Autowired
    private MedicalReportService medicalReportService;

    @Autowired
    private ResearchService researchService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private StaffMemberService staffMemberService;

    @GetMapping("/create/{id}")
    public String showPage(Model model, @PathVariable Long id, HttpSession session) {
        String token = (String) session.getAttribute("jwtToken");
        Claims claims = jwtUtil.extractAllClaims(token);
        Long personId = claims.get("id", Long.class);

        StaffMemberDTO staffMemberDTO = staffMemberService.getStaffMemberByPersonId(personId);
        if (staffMemberDTO == null) {
            throw new IllegalArgumentException("Staff member not found for person ID: " + personId);
        }
        MedicalReportDTO medicalReportDTO = new MedicalReportDTO();
        medicalReportDTO.setStaffMember(staffMemberDTO);

        model.addAttribute("staffMember", staffMemberDTO);
        model.addAttribute("research", researchService.getResearchById(id));
        model.addAttribute("report", medicalReportDTO);
        return "make_report";
    }

    @PostMapping("/{id}")
    public String createMedicalReport(@ModelAttribute MedicalReportDTO dto, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        dto.setResearch(researchService.getResearchById(id));
        System.out.println(dto);
        try {
            medicalReportService.createMedicalReport(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Medical report created successfully!");
            return "redirect:/researches/all";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/medical-reports/create/" + id;
        }
    }



    @GetMapping("/show")
    public String show(Model model){
        model.addAttribute("report", new MedicalReportDTO());
        return "make_report";
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<MedicalReportDTO> getMedicalReportById(@PathVariable Long id) {
//        MedicalReportDTO dto = medicalReportService.getMedicalReportById(id);
//        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
//    }

    @GetMapping
    public String getAllReports(Model model) {
        List<MedicalReportDTO> medicalReportDTOList = medicalReportService.getAllReports();
        model.addAttribute("reports", medicalReportDTOList);
        return "medical_reports";
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedicalReport(@PathVariable Long id) {
        medicalReportService.deleteMedicalReport(id);
        return ResponseEntity.ok("MedicalReport deleted successfully");
    }
}
