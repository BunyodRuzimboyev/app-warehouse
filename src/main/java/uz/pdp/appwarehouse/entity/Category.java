package uz.pdp.appwarehouse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouse.entity.template.AbstractEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Category  extends AbstractEntity {

    @JsonIgnore
    //@ManyToOne(cascade = CascadeType.REMOVE)
    @ManyToOne
    private Category parentCategory;

    @JsonProperty(value = "parent_id")
    public Integer parentId(){
        return parentCategory!=null? parentCategory.getId():null;
    }

    @JsonProperty(value = "parent_name")
    public String parentName(){
        return parentCategory!=null?parentCategory.getName():null;
    }
}
