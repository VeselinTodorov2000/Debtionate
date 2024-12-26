package com.example.deptionate.web;

import com.example.deptionate.entity.Debt;
import com.example.deptionate.entity.User;
import com.example.deptionate.mapper.DebtMapper;
import com.example.deptionate.model.DebtDto;
import com.example.deptionate.service.DebtService;
import com.example.deptionate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/debts")
@CrossOrigin(origins = "http://localhost:4200")
public class DebtController {
    @Autowired
    private DebtService debtService;
    @Autowired
    private UserService userService;
    @Autowired
    private DebtMapper debtMapper;

    @PostMapping("/create")
    public ResponseEntity<DebtDto> create(@RequestBody DebtDto debtDto) {
        Optional<User> user = userService.findById(debtDto.getUserId());
        Debt debt = debtService.create(debtMapper.mapDtoToEntity(debtDto, user.orElseGet(null)));
        if (debt != null) {
            DebtDto createdDebtDto = debtMapper.mapEntityToDto(debt);
            return new ResponseEntity<>(createdDebtDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DebtDto> getById(@PathVariable Long id) {
        Optional<Debt> debt = debtService.findById(id);
        if (debt.isPresent()) {
            DebtDto debtDto = debtMapper.mapEntityToDto(debt.get());
            return new ResponseEntity<>(debtDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{id}")
    List<DebtDto> findDebtsByUserId(@PathVariable Long id) {
        return debtService.findDebtsByUserId(id).stream()
                .map(debtMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @PutMapping
    public ResponseEntity<DebtDto> update(@RequestBody DebtDto debtDto) {
        Optional<User> user = userService.findById(debtDto.getUserId());
        Debt debt = debtMapper.mapDtoToEntity(debtDto, user.orElseGet(null));
        return new ResponseEntity<>(debtMapper.mapEntityToDto(debtService.update(debt)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (debtService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
