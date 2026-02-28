package com.jiandaoyun.service.core;

import com.jiandaoyun.domain.metadata.FormDefinition;
import java.util.Map;

/**
 * 閺佺増宓侀弽锟犵崣閺堝秴濮熼幒銉ュ經閵? *
 *
 * @author Codex
 *
 * @since 2026/02/28
 */

public interface ValidatorService {

    /**
     * 閹稿銆冮崡鏇炵暰娑斿鐗庢灞惧絹娴溿倖鏆熼幑顔衡偓?     *
 *
 * @param formDefinition 鐞涖劌宕熺€规矮绠?
 *
 * @param data 閹绘劒姘﹂弫鐗堝祦
     */
    void validateSubmission(FormDefinition formDefinition, Map<String, Object> data);
}