package com.example.socksapp.model.socks;

import com.example.socksapp.model.socks.Colors;
import com.example.socksapp.model.socks.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Socks {
    private Colors color;
    private Size size;
    private int cuttonPart;

}
