package com.mycompany.testforegartech.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "instruments")
public class Instrument implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull(message = "Placement date is required")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "PLACEMENT_DATE")
    private Date placementDate;

    @NotBlank(message = "Name is required")
    @Column(name = "name")
    private String name;

//    @NotNull(message = "Cost is required")
//    @Digits(integer = 1, fraction = 0, message = "Invalid cost")
    @Column(name = "cost")
    private Long cost;

    @Version
    @Column(name = "version")
    private int version;

    public Instrument() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPlacementDate() {
        return placementDate;
    }

    public void setPlacementDate(Date placementDate) {
        this.placementDate = placementDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Transient
    public String getPlacementDateToString() {
        String placementDateString = "";
        if (placementDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            placementDateString = sdf.format(placementDate);
        }
        return placementDateString;
    }

//    @Transient
//    public void getPlacementDate2(String placementDateString) {
//        if (!placementDateString.equals("")) {
//            try {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                placementDate = sdf.parse(placementDateString);
//            } catch (ParseException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }

    @Override
    public String toString() {
        return "Instrument{" +
                "id=" + id +
                ", placementDate=" + placementDate +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }

}
