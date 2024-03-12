package com.lyuban.m4banktask.DTO;

import java.util.Objects;

public class SumResponseDTO extends ResponseDTO{
    private Integer sum;

    public SumResponseDTO(Integer sum, int code, String description) {
        super(code, description);
        this.sum = sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public Integer getSum() {
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SumResponseDTO that = (SumResponseDTO) o;
        return sum == that.sum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sum);
    }

    @Override
    public String toString() {
        return "SumResponseDTO{" +
                "sum=" + sum +
                '}';
    }
}
