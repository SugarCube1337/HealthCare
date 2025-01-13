package se.ifmo.healthcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ifmo.healthcare.dao.StaffMemberDAO;
import se.ifmo.healthcare.dto.StaffMemberDTO;
import se.ifmo.healthcare.mappers.StaffMemberMapper;
import se.ifmo.healthcare.models.StaffMember;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffMemberService {

    @Autowired
    private StaffMemberDAO staffMemberDAO;

    public void createStaffMember(StaffMemberDTO staffMemberDTO) {
        StaffMember staffMember = StaffMemberMapper.toEntity(staffMemberDTO);
        staffMemberDAO.save(staffMember);
    }

    public StaffMemberDTO getStaffMemberById(Long id) {
        StaffMember staffMember = staffMemberDAO.findById(id);
        return staffMember == null ? null : StaffMemberMapper.toDTO(staffMember);
    }

    public List<StaffMemberDTO> getAllStaffMembers() {
        return staffMemberDAO.findAll().stream()
                .map(StaffMemberMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void updateStaffMember(StaffMemberDTO staffMemberDTO) {
        StaffMember staffMember = StaffMemberMapper.toEntity(staffMemberDTO);
        staffMemberDAO.update(staffMember);
    }

    public void deleteStaffMember(Long id) {
        staffMemberDAO.delete(id);
    }
}
