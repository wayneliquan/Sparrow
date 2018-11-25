package com.wayne.sparrow.app.pojo.order;

import lombok.Data;

import java.util.List;

@Data
public class Contract {

    Long id ;

    List<ContractItem> itemList;
}
