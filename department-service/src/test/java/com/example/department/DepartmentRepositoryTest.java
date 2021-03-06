package com.example.department;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.util.Assert;

import com.example.department.model.Department;
import com.example.department.service.DepartmentService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentRepositoryTest {

    private static DepartmentService service = new DepartmentService();

    @Test
    public void testAddDepartment() {
        Department department = new Department();
        department.setId(1l);
        department.setDepartmentName("testing");
        department = service.add(department);
        Assert.notNull(department, "Department is null.");
        Assert.isTrue(department.getId() == 1L, "Department bad id.");
    }

    @Test
    public void testFindAll() {
        List<Department> departments = service.findAll();
        Assert.isTrue(departments.size() == 1, "Departments size is wrong.");
        Assert.isTrue(departments.get(0).getId() == 1L, "Department bad id.");
    }

}
