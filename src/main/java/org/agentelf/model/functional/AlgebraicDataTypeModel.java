package org.agentelf.model.functional;

import org.agentelf.sourcebuilder.SourceBuilder;

import java.util.List;

public record AlgebraicDataTypeModel(String packageName,
                                     List<ProductType> product,
                                     List<SumType> sum) {

    public void addToBuilder(SourceBuilder sourceBuilder) {
        for(SumType type : sum) {
            type.addToBuilder(packageName,sourceBuilder);
        }
        for(ProductType type : product) {
            type.addToBuilder(packageName,sourceBuilder);
        }
    }

}
