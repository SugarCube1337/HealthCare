package se.ifmo.healthcare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.ifmo.healthcare.dao.BiomaterialDAO;
import se.ifmo.healthcare.dto.BiomaterialDTO;
import se.ifmo.healthcare.dto.DiagnosisDTO;
import se.ifmo.healthcare.models.Biomaterial;
import se.ifmo.healthcare.services.BiomaterialService;
import se.ifmo.healthcare.services.DiagnosisService;

import java.util.List;

@Controller
@RequestMapping("/biomaterials")
public class BiomaterialController {
    @Autowired
    private BiomaterialService biomaterialService;

    @Autowired
    private BiomaterialDAO biomaterialDAO;

    @PostMapping
    public ResponseEntity<String> createBiomaterial(@RequestBody BiomaterialDTO dto) {
        biomaterialService.createBiomaterial(dto);
        return ResponseEntity.ok("Biomaterial created successfully");
    }

    @GetMapping("/all")
    public String showPage(Model model){
        List<BiomaterialDTO> biomaterials = biomaterialService.getAllBiomaterials();
        model.addAttribute("biomaterials", biomaterials);
        return "biomaterials";
    }

    @GetMapping("/expired")
    public String showExpired(Model model){
        List<BiomaterialDTO> biomaterialDTOS = biomaterialService.getExpiredBiomaterials();
        model.addAttribute("biomaterials", biomaterialDTOS);
        System.out.println(biomaterialDTOS);
        return "nurse_dashboard";
    }
    @GetMapping("/lab")
    public String showLab(Model model){
        List<BiomaterialDTO> biomaterialDTOS = biomaterialService.getLabBiomaterials();
        model.addAttribute("biomaterials", biomaterialDTOS);
        System.out.println(biomaterialDTOS);
        return "biomaterial_information";
    }




    @PostMapping("/update")
    public String updateBiomaterial(@ModelAttribute BiomaterialDTO biomaterialDTO) {
        biomaterialService.updateBiomaterial(biomaterialDTO);
        return "redirect:/biomaterials/all";
    }

    @GetMapping("/update/{id}")
    public String updateBiomaterialPage(@PathVariable Long id, Model model) {
        BiomaterialDTO biomaterial = biomaterialService.getBiomaterialById(id);
        model.addAttribute("biomaterial", biomaterial);
        return "update_biomaterial";
    }



    @PostMapping("/send/{id}")
    public ResponseEntity<String> sendBiomaterialToNurse(@PathVariable Long id) {
        biomaterialService.sendToNurse(id);
        return ResponseEntity.ok("Biomaterial sent to nurse successfully.");
    }

    @PostMapping("/send-to-lab/{id}")
    public ResponseEntity<String> sendBiomaterialToLab(@PathVariable Long id) {
        biomaterialService.sendToLab(id);
        return ResponseEntity.ok("Biomaterial sent to laboratory successfully.");
    }


    @GetMapping("/{id}")
    public ResponseEntity<BiomaterialDTO> getBiomaterialById(@PathVariable Long id) {
        BiomaterialDTO dto = biomaterialService.getBiomaterialById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public String getAllBiomaterials(Model model) {
        List<BiomaterialDTO> biomaterials = biomaterialService.getAllBiomaterials();
        model.addAttribute("biomaterials", biomaterials);
        return "biomaterials";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBiomaterial(@PathVariable Long id) {
        biomaterialService.deleteBiomaterial(id);
        return ResponseEntity.ok("Biomaterial deleted successfully");
    }
}
