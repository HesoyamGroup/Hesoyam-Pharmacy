package com.hesoyam.pharmacy.user.mapper;

import com.hesoyam.pharmacy.user.dto.EmployeeBasicDTO;
import com.hesoyam.pharmacy.user.model.Dermatologist;

import java.util.ArrayList;
import java.util.List;

public class DermatologistMapper {
    private DermatologistMapper(){
        //Implicit empty ctor hidden
    }

    public static List<EmployeeBasicDTO> getBasicInfoList(List<Dermatologist> dermatologists){
        List<EmployeeBasicDTO> retVal = new ArrayList<>();
        dermatologists.forEach(dermatologist -> retVal.add(new EmployeeBasicDTO(dermatologist)));
        return retVal;
    }

}
