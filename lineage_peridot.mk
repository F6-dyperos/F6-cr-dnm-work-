#
# Copyright (C) 2024 The LineageOS Project
#
# SPDX-License-Identifier: Apache-2.0
#

# Inherit from those products. Most specific first.
$(call inherit-product, $(SRC_TARGET_DIR)/product/core_64_bit_only.mk)
$(call inherit-product, $(SRC_TARGET_DIR)/product/full_base_telephony.mk)

# Inherit some common Lineage stuff.
$(call inherit-product, vendor/lineage/config/common_full_phone.mk)

# Inherit from peridot device
$(call inherit-product, device/xiaomi/peridot/device.mk)

# Inherit from the MiuiCamera setup
$(call inherit-product-if-exists, device/xiaomi/peridot-miuicamera/device.mk)

PRODUCT_NAME := lineage_peridot
PRODUCT_DEVICE := peridot
PRODUCT_MANUFACTURER := Xiaomi
PRODUCT_BRAND := POCO
PRODUCT_MODEL := 24069PC21G

#crdroid
TARGET_HAS_UDFPS := true
TARGET_BOOT_ANIMATION_RES := 1080
EXTRA_UDFPS_ANIMATIONS := true

PRODUCT_SYSTEM_NAME := peridot_global
PRODUCT_SYSTEM_DEVICE := peridot

PRODUCT_BUILD_PROP_OVERRIDES += \
    BuildDesc="peridot_global-user 14 UKQ1.240116.001 V816.0.12.0.UNPMIXM release-keys" \
    BuildFingerprint=POCO/peridot_global/peridot:14/UKQ1.240116.001/V816.0.12.0.UNPMIXM:user/release-keys \
    DeviceName=peridot
    DeviceProduct=peridot_global \
    SystemName=peridot_global \
    SystemDevice=peridot

PRODUCT_GMS_CLIENTID_BASE := android-xiaomi
